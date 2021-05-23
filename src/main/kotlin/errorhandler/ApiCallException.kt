package errorhandler

import errorhandler.entity.ServiceError

class ApiCallException(apiError: ServiceError): RuntimeException("Api call Error: ${apiError.code} ${apiError.title} \n ${apiError.message?:""}")