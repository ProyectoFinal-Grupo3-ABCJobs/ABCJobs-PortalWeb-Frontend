package com.example.proyectoabckotlin.main


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
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
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Test_EvaluacionDesempeno {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_EvaluacionDesempeno() {
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
        appCompatEditText.perform(replaceText("empresa"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
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
        appCompatEditText2.perform(replaceText("empresa"), closeSoftKeyboard())

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

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.evaluacion_desempeno_button),
                withText("Employee Performance Evaluation"),
                withContentDescription("Button to go to the employee performance evaluation record"),
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
        appCompatButton2.perform(click())

        val materialTextView = onData(anything())
            .inAdapterView(
                allOf(
                    withId(R.id.listView),
                    childAtPosition(
                        withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                        3
                    )
                )
            )
            .atPosition(0)
        materialTextView.perform(click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.calificacion_registro_edit), withContentDescription("Enter a score"),
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
        appCompatEditText3.perform(replaceText("80/100"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.aspectosResaltar_registro_edit),
                withContentDescription("Enter aspects to highlight"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText("spectos a resaltar"), closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.aspectos_mejorar_registro_edit),
                withContentDescription("Enter aspects to improve"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("aspectos mejorar"), closeSoftKeyboard())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.registro_desempeno_button),
                withText("Save"),
                withContentDescription("Button to save "),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        val imageView = onView(
            allOf(
                withId(androidx.appcompat.R.id.search_button), withContentDescription("Search"),
                withParent(
                    allOf(
                        withId(androidx.appcompat.R.id.search_bar),
                        withParent(withId(R.id.searchView))
                    )
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))
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
