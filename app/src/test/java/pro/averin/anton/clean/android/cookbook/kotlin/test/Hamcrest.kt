package pro.averin.anton.clean.android.cookbook.kotlin.test

import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.junit.Assert

fun <T> assertThat(actual: T, matcher: Matcher<in T>) = Assert.assertThat(actual, matcher)

// ==============================================================================
// Matchers
// ==============================================================================

// ------------------------------------------------------------------------------
// aliases
// ------------------------------------------------------------------------------

fun isTrue() = isEqualTo(true)
fun isFalse() = isEqualTo(false)

fun isEmptyString() = isEqualTo("")

fun <T> isNot(value: T) = CoreMatchers.`is`(CoreMatchers.not(value))
fun <T> isNot(value: Matcher<T>) = CoreMatchers.`is`(CoreMatchers.not(value))
fun nullValue() = CoreMatchers.nullValue()

// ------------------------------------------------------------------------------
// simple delegation
// ------------------------------------------------------------------------------

fun <T> isEqualTo(value: T) = CoreMatchers.`is`(value)
fun <T> isEqualTo(matcher: Matcher<T>) = CoreMatchers.`is`(matcher)

fun <T> sameInstance(value: T) = CoreMatchers.sameInstance(value)

fun <T> not(value: T) = CoreMatchers.not(value)
fun <T> not(value: Matcher<T>) = CoreMatchers.not(value)


fun <T> isA(type: Class<T>) = CoreMatchers.isA(type)

fun <T> hasItem(item: T) = CoreMatchers.hasItem(item)
