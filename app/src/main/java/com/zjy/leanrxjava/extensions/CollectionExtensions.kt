package com.zjy.leanrxjava.extensions

/**

 *Description:
 *@author:zhou.junyou
 *
 *Create by:Android Studio
 *Date:2017/12/8

 */

/**
动机

在Java中，我们将类命名为“*Utils”：FileUtils、StringUtils 等，著名的 java.util.Collections 也属于同一种命名方式。 关于这些 Utils-类的不愉快的部分是代码写成这样：

// Java
Collections.swap(list, Collections.binarySearch(list, Collections.max(otherList)), Collections.max(list));
这些类名总是碍手碍脚的，我们可以通过静态导入达到这样效果：

// Java
swap(list, binarySearch(list, max(otherList)), max(list));
这会变得好一点，但是我们并没有从 IDE 强大的自动补全功能中得到帮助。如果能这样就更好了：

// Java
list.swap(list.binarySearch(otherList.max()), list.max());
但是我们不希望在 List 类内实现这些所有可能的方法，对吧？这时候扩展将会帮助我们。
 *
 */
inline fun <E: Any, T: Collection<E>> T?.withNotNullNorEmpty(func: T.() -> Unit): Unit {
    if (this != null && this.isNotEmpty()) {
        with (this) { func() }
    }
}