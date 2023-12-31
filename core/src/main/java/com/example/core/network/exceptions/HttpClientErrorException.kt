package com.example.core.network.exceptions

sealed class HttpClientErrorException(val code : Int, message : String) : Exception(message) {

    class BadRequest: HttpClientErrorException(400, "Bad Request")
    class Unauthorized: HttpClientErrorException(401, "Unauthorized")
    class PaymentRequired: HttpClientErrorException(402, "Payment Required")
    class Forbidden: HttpClientErrorException(403, "Forbidden")
    class NotFound: HttpClientErrorException(404, "Not Found")
    class MethodNotAllowed: HttpClientErrorException(405, "Method Not Allowed")
    class NotAcceptable: HttpClientErrorException(406, "Not Acceptable")
    class ProxyAuthenticationRequired: HttpClientErrorException(407, "Proxy Authentication Required")
    class RequestTimeout: HttpClientErrorException(408, "Request Timeout")
    class Conflict: HttpClientErrorException(409, "Conflict")
    class Gone: HttpClientErrorException(410, "Gone")
    class LengthRequired: HttpClientErrorException(411, "Length Required")
    class PreconditionFailed: HttpClientErrorException(412, "Precondition Failed")
    class RequestTooLong: HttpClientErrorException(413, "Payload Too Large")
    class RequestUriTooLong: HttpClientErrorException(414, "URI Too Long")
    class UnsupportedMediaType: HttpClientErrorException(415, "Unsupported Media Type")
    class RequestedRangeNotSatisfiable: HttpClientErrorException(416, "Range Not Satisfiable")
    class ExpectationFailed: HttpClientErrorException(417, "Expectation Failed")
    class MisdirectedRequest: HttpClientErrorException(421, "Misdirected Request")
    class UnprocessableEntity: HttpClientErrorException(422, "Unprocessable Entity")
    class Locked: HttpClientErrorException(423, "Locked")
    class FailedDependency: HttpClientErrorException(424, "Failed Dependency")
    class TooEarly: HttpClientErrorException(425, "Too Early")
    class UpgradeRequired: HttpClientErrorException(426, "Upgrade Required")
    class PreconditionRequired: HttpClientErrorException(428, "Precondition Required")
    class TooManyRequests: HttpClientErrorException(429, "Too Many Requests")
    class RequestHeaderFieldsTooLarge: HttpClientErrorException(431, "Request Header Fields Too Large")
    class UnavailableForLegalReasons: HttpClientErrorException(451, "Unavailable For Legal Reasons")

    companion object{
        fun create(code : Int): HttpClientErrorException{
            return when(code){
                400 -> BadRequest()
                401 -> Unauthorized()
                402 -> PaymentRequired()
                403 -> Forbidden()
                404 -> NotFound()
                405 -> MethodNotAllowed()
                406 -> NotAcceptable()
                407 -> ProxyAuthenticationRequired()
                408 -> RequestTimeout()
                409 -> Conflict()
                410 -> Gone()
                411 -> LengthRequired()
                412 -> PreconditionFailed()
                413 -> RequestTooLong()
                414 -> RequestUriTooLong()
                415 -> UnsupportedMediaType()
                416 -> RequestedRangeNotSatisfiable()
                417 -> ExpectationFailed()
                421 -> MisdirectedRequest()
                422 -> UnprocessableEntity()
                423 -> Locked()
                424 ->  FailedDependency()
                425 -> TooEarly()
                426 -> UpgradeRequired()
                428 -> PreconditionRequired()
                429 -> TooManyRequests()
                431 -> RequestHeaderFieldsTooLarge()
                451 -> UnavailableForLegalReasons()
                else -> throw IllegalArgumentException()
            }
        }
    }

}
