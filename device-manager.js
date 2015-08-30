"use strict";

var logger = require("node-logger");
var IllegalArgumentError = require('exceptions').IllegalArgumentError;

var mongo_url = 'mongodb://localhost/product';

var mongoose = require('mongoose');
require('./model/product_model.js')();
var ProductModel = mongoose.model("ProductModel");

function DeviceManager() {
}

/**
 * Get product model
 * @param {string} Product model
 */
DeviceManager.prototype.getProductsModel =  function (callback) {

    mongoose.connect(mongo_url, function (err) {
        logger.info("DeviceManager::getProductsModel : 03");
        if (err) return callback(new IllegalArgumentError('Product model não encontrado.'));
        var query = ProductModel.find({});
        query.sort('-trade');

        query.exec(function (err, products) {
            logger.info("DeviceManager::getProductsModel : 05");
        if (err) return callback(new IllegalArgumentError('Product model não encontrado.'));
        logger.info("DeviceManager::getProductsModel : 06");
            mongoose.disconnect();
            logger.info("DeviceManager::getProductsModel : 07");
            callback(null, products);
        });
    });
};

/**
 * Get product model
 * @param {string} Product model
 */
DeviceManager.prototype.getProductModel =  function (_bar_code, callback) {
logger.info("DeviceManager::getProductModel : "+_bar_code);
    if (!_bar_code) {
        logger.info("DeviceManager::getProductModel : 01");
        return callback(new IllegalArgumentError('Alguns parâmetros estão vazios.'));
    }
logger.info("DeviceManager::getProductModel : 02");
    mongoose.connect(mongo_url, function (err) {
        logger.info("DeviceManager::getProductModel : 03");
        if (err) return callback(new IllegalArgumentError('Product model não encontrado.'));
        var query = ProductModel.findOne({ bar_code:_bar_code});
        //query.sort('-date_update');
logger.info("DeviceManager::getProductModel : 04");
        query.exec(function (err, product) {
            logger.info("DeviceManager::getProductModel : 05");
        if (err) return callback(new IllegalArgumentError('Products model não encontrado.'));
            mongoose.disconnect();
            callback(null, product);
        });
    });
};

DeviceManager.prototype.setProductModel =  function (_bar_code, _product, _trade, _price, callback) {
    logger.info("DeviceManager::setProductModel : "+_bar_code);
    if (!_bar_code && !_product && !_trade && !_price) {
        logger.info("DeviceManager::setProductModel 01");
        return callback(new IllegalArgumentError('Alguns parâmetros estão vazios.'));
    }
    logger.info("DeviceManager::setProductModel 02");
    mongoose.connect(mongo_url, function (err) {
        logger.info("DeviceManager::setProductModel 03");
        if (err) return callback(new IllegalArgumentError('Product model não encontrado.'));
        logger.info("DeviceManager::setProductModel 04");
        var product = new ProductModel({
            bar_code: _bar_code,
            product: _product,
            trade: _trade,
            price: _price
        });

        product.save(function (err, product) {
            if (err) return callback(new IllegalArgumentError('Product model não encontrado.'));

            mongoose.disconnect();
            callback(null, _bar_code);
        });
    });
};

var deviceManager = new DeviceManager();

// exports
module.exports = deviceManager;
