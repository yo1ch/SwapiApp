package com.example.core.network.exceptions

sealed class HttpServerErrorException(val code : Int, message : String) : Exception(message) {

    class InternalServerError: HttpServerErrorException(500, "Internal Server Error")
    class NotImplemented: HttpServerErrorException(501, "Not Implemented")
    class BadGateway: HttpServerErrorException(502, "Bad Gateway")
    class ServiceUnavailable: HttpServerErrorException(503, "Service Unavailable")
    class GatewayTimeout: HttpServerErrorException(504, "Gateway Timeout")
    class HttpVersionNotSupported: HttpServerErrorException(505, "HTTP Version Not Supported")
    class VariantAlsoNegotiates: HttpServerErrorException(506, "Variant Also Negotiates")
    class InsufficientStorage: HttpServerErrorException(507, "Insufficient Storage")
    class LoopDetected: HttpServerErrorException(508, "Loop Detected")
    class NotExtended: HttpServerErrorException(510, "Not Extended")
    class NetworkAuthenticationRequired: HttpServerErrorException(511, "Network Authentication Required")

    companion object{
        fun create(code : Int): HttpServerErrorException{
            return when(code){
                500 -> InternalServerError()
                501 -> NotImplemented()
                502 -> BadGateway()
                503 -> ServiceUnavailable()
                504 -> GatewayTimeout()
                505 -> HttpVersionNotSupported()
                506 -> VariantAlsoNegotiates()
                507 -> InsufficientStorage()
                508 -> LoopDetected()
                510 -> NotExtended()
                511 -> NetworkAuthenticationRequired()
                else -> throw IllegalArgumentException()
            }
        }
    }

}