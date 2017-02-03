package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.nylonobjects.ListObject

/**
 * Created by Aedan Smith.
 */

class CreateListFromStack : NylonFunction("l.nl") {
    override fun apply(stack: NylonStack) {
        val list = ListObject(stack.toList())
        stack.clear()
        stack.add(list)
    }
}
