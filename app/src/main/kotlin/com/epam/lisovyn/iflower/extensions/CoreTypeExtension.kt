package com.epam.lisovyn.iflower.extensions

fun <K: Any,V: Any> List<Pair<K,V>>.toMap(): Map<K,V> = mapOf(*this.toTypedArray())