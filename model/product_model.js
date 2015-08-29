var mongoose = require('mongoose');
var Schema = mongoose.Schema;

module.exports = function () {
    var productModelSchema = new Schema({
        bar_code: { type: String, index: true },
        product: { type: String, index: true },
        trade: { type: String, index: true },
        price: { type: String, index: false }
        //context_model: mongoose.Schema.Types.Mixed,
        //date_update: { type: Date, default: Date.now }
    });

    mongoose.model('ProductModel', productModelSchema);
};
