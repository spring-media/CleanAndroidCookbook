package pro.averin.anton.clean.android.cookbook.kotlin.test

import org.mockito.BDDMockito
import org.mockito.Mockito
import org.mockito.verification.VerificationMode

fun <T> given(mock: T) = BDDMockito.given(mock)

fun times(wantedNumberOfTimes: Int) = BDDMockito.times(wantedNumberOfTimes)

fun atLeastOnce() = Mockito.atLeastOnce()
fun atLeast(minNumberOfTimes: Int) = Mockito.atLeast(minNumberOfTimes)

fun once() = BDDMockito.times(1)
fun twice() = BDDMockito.times(2)

inline fun <reified T : Any> argThat(noinline predicate: T.() -> Boolean)
        = BDDMockito.argThat(ArgThat<T>(predicate)) ?: createInstance(T::class)

inline fun <reified T : Any> mock() = Mockito.mock(T::class.java)
fun <T : Any> spy(value: T) = Mockito.spy(value)

fun <T> verify(mock: T) = Mockito.verify(mock)
fun <T> verify(mock: T, mode: VerificationMode) = Mockito.verify(mock, mode)
fun <T> verifyNoMoreInteractions(mock: T) = Mockito.verifyNoMoreInteractions(mock)
fun <T> reset(mock: T) = Mockito.reset(mock)

fun inOrder(vararg value: Any) = Mockito.inOrder(*value)
fun never() = Mockito.never()

inline fun <reified T : Any> eq(value: T) = Mockito.eq(value) ?: createInstance<T>()
inline fun <reified T : Any> anyArray(): Array<T> = Mockito.any(Array<T>::class.java) ?: arrayOf()
inline fun <reified T : Any> any() = Mockito.any(T::class.java) ?: createInstance<T>()
inline fun <reified T : Any> isNull(): T? = Mockito.isNull(T::class.java)

