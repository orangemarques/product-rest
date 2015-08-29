"use strict";

/**
 * Extends error to indicate object not found
 */
function ObjectNotFoundError(msg) {
    this.name = "ObjectNotFoundError";
    this.message = msg;
}

ObjectNotFoundError.prototype = new Error();
ObjectNotFoundError.prototype.constructor = ObjectNotFoundError;

/*
 * Extends error to indicate object not found
 */
function IllegalArgumentError(msg) {
    this.name = "IllegalArgumentError";
    this.message = msg;
}

IllegalArgumentError.prototype = new Error();
IllegalArgumentError.prototype.constructor = IllegalArgumentError;

/**
 * Extends error to indicate object already exists
 */
function ObjectAlreadyExistsError(msg) {
    this.name = "ObjectAlreadyExistsError";
    this.message = msg;
}

ObjectAlreadyExistsError.prototype = new Error();
ObjectAlreadyExistsError.prototype.constructor = ObjectAlreadyExistsError;

/**
 * Extends error to indicate internal error
 */
function GeneralInternalError(msg) {
    this.name = "GeneralInternalError";
    this.message = msg;
}

GeneralInternalError.prototype = new Error();
GeneralInternalError.prototype.constructor = GeneralInternalError;

module.exports = {
    ObjectNotFoundError: ObjectNotFoundError,
    IllegalArgumentError: IllegalArgumentError,
    ObjectAlreadyExistsError: ObjectAlreadyExistsError,
    GeneralInternalError: GeneralInternalError
};
