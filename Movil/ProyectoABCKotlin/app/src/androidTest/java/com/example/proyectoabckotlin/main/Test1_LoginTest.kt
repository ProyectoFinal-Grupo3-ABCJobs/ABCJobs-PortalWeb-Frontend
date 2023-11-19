package com.example.proyectoabckotlin.main


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.proyectoabckotlin.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Test1_LoginTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loginTest() {
        val button = onView(
            allOf(
                withId(R.id.ingresar_login_button),
                withText("LOGIN"),
                withContentDescription("Button to enter"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val appCompatEditText = onView(
            allOf(
                withId(R.id.usuario_login_edit), withContentDescription("Enter the user"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(click())
        Thread.sleep(5_000)

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.usuario_login_edit), withContentDescription("Enter the user"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(longClick())
        Thread.sleep(5_000)

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.usuario_login_edit), withContentDescription("Enter the user"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(longClick())
        Thread.sleep(5_000)

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.usuario_login_edit), withContentDescription("Enter the user"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText("candidato"), closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.contrasena_login_edit), withContentDescription("Enter the password"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("candidato"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.ingresar_login_button),
                withText("Login"),
                withContentDescription("Button to enter"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())
        Thread.sleep(5_000)
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
