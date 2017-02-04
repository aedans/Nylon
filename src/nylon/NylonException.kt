package nylon

/**
 * Created by Aedan Smith.
 */

open class NylonException(s: String?) : RuntimeException(s) {
    constructor(e: Exception, function: NylonFunction) : this("${e.message} \n\tat $function") {
        this.stackTrace = e.stackTrace
    }
}
