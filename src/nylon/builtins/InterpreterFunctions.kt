package nylon.builtins

import nylon.NylonFunction
import nylon.NylonStack
import nylon.nylonobjects.ListObject
import java.util.*

/**
 * Created by Aedan Smith.
 */

class CreateListFromStack : NylonFunction("l") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
        val list = ListObject(stack.toList())
        stack.clear()
        stack.add(list)
    }
}
