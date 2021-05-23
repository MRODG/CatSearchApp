package api.entity

import errorhandler.entity.ServiceError

data class ResponseEntity(val directions: List<String>? = null, val message: String? = null, val serviceError: ServiceError? = null)