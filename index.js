"use strict";

var restify = require('restify');
var cluster = require('cluster');
var logger = require("node-logger");
var deviceManager = require('./device-manager');

var IllegalArgumentError = require('exceptions').IllegalArgumentError;
var ObjectAlreadyExistsError = require('exceptions').ObjectAlreadyExistsError;
var UnsupportedMediaTypeError = require('exceptions').UnsupportedMediaTypeError;
var EmptyParametersErrorView = require('exceptions').EmptyParametersErrorView;
var GeneralInternalServerErrorView = require('exceptions').GeneralInternalServerErrorView;

function getProductModel(req, res, next) {
    logger.info("getProductModel : "+req.params.bar_code);
    var _bar_code = req.params.bar_code;
logger.info("getProductModel : 01");
    if (!_bar_code) {
        logger.info("getProductModel : 02");
        return next(new EmptyParametersErrorView(
        'Alguns parâmetros vazio. Por favor, consulte a documentação da API.'
        ));
    }
logger.info("getProductModel : 03");
    deviceManager.getProductModel(_bar_code, function (err, data) {
        logger.info("getContextModel called!");
        if (err === null) {
            logger.info("[END] Context model generated: " + data.bar_code);
            res.send(200, data);

        } else if (err instanceof IllegalArgumentError) {
            logger.error(err);
            return next(new EmptyParametersErrorView(
            'lguns parâmetros vazio. Por favor, consulte a documentação da API.')
            );

        } else if (err instanceof ObjectAlreadyExistsError) {
            logger.error(err);
            res.send(409, data);

        } else if (err instanceof Error) {
            logger.error(err);
            return next(new GeneralInternalServerErrorView(
            'Erro inesperado ocorreu quando tentava pegar o produto')
            );
        }
    });
}

function setProductModel(req, res, next) {
    deviceManager.setProductModel(req.headers.bar_code, req.headers.product, req.headers.trade, req.headers.price, function (err, data) {
        if (err === null) {
            logger.info("[END] Produto criado: " + data);
            res.send(200, data);

        } else if (err instanceof IllegalArgumentError) {
            logger.error(err);
            return next(new EmptyParametersErrorView(
            'algum parâmetro em branco. Por favor, consulte a documentação da API.')
            );

        } else if (err instanceof ObjectAlreadyExistsError) {
            logger.error(err);
            res.send(409, data);

        } else if (err instanceof Error) {
            logger.error(err);
            return next(new GeneralInternalServerErrorView(
            'Erro inesperado ocorreu quando tentava salvar o produto')
            );
        }
    });
}

//Number of CPUs to be used on cluster
var numCPUs = require('os').cpus().length;

//Initialize the cluster
if (cluster.isMaster) {
    // Fork workers.
    for (var i = 0; i < numCPUs; i++) {
        cluster.fork();
    }

    cluster.on('exit', function (worker, code, signal) {
        logger.error('worker ' + worker.process.pid + ' died with signal' + signal);
    });

    cluster.on('disconnect', function (worker) {
        logger.error('worker ' + worker.process.pid + ' disconnected');
    });

} else {
    //Initialize Rest Service
    var server = restify.createServer({
        name: 'engine'
    });

    server.use(restify.acceptParser(server.acceptable));
    server.use(restify.dateParser());
    server.use(restify.queryParser());
    server.use(restify.jsonp());
    server.use(restify.bodyParser());

    server.get('/api/v1/application/product/:bar_code?', getProductModel);
    server.post('/api/v1/application/product/', setProductModel);

    server.listen(process.env.ENGINE_PORT || 8299, function () {
        logger.info('Servidor ProductREST rodando em: ' + server.url);
    });
}
