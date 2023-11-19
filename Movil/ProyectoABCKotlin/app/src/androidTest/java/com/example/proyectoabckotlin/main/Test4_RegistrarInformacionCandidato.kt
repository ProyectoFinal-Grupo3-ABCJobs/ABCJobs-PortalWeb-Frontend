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
import com.example.proyectoabckotlin.registroCandidato.RegistroInfoCandidatoActivity
import com.example.proyectoabckotlin.registroCandidato.RegistroInfoEduCandidatoActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Test4_RegistrarInformacionCandidato {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun registrarInformacionCandidato() {
        val appCompatButton = onView(
            allOf(
                withId(R.id.registrar_candidato_login_button),
                withText("Register Candidate"),
                withContentDescription("Button to register candidate"),
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
        appCompatButton.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.usuario_registro_edit), withContentDescription("Enter the user"),
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
        appCompatEditText.perform(replaceText("matias@correo.com"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.contrasena_registro_edit), withContentDescription("Enter the password"),
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
        appCompatEditText2.perform(replaceText("matias"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.repetir_contrasena_registro_edit),
                withContentDescription("Repeat the password again"),
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
        appCompatEditText3.perform(replaceText("matias"), closeSoftKeyboard())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.continuar_registro_button),
                withText("Continue"),
                withContentDescription("Button to continue"),
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
        appCompatButton2.perform(click())
        Thread.sleep(1_000)

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.nombreEditText), withContentDescription("Enter Name"),
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
        appCompatEditText4.perform(replaceText("Matias"), closeSoftKeyboard())

        onView(withId(R.id.tipoIdentificacionSpinner))
            .perform(click())

        onData(allOf(`is`(Matchers.instanceOf(RegistroInfoCandidatoActivity.TipoIdentificacion::class.java)), `is`(
            RegistroInfoCandidatoActivity.TipoIdentificacion("CC", "Cédula")
        )))
            .perform(click())


        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.identificacionEditText), withContentDescription("Enter ID"),
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
        appCompatEditText5.perform(replaceText("1478744"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.direccionEditText), withContentDescription("Enter residence address"),
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
        appCompatEditText6.perform(replaceText("calle 3 #2-34"), closeSoftKeyboard())


        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.telefonoEditText), withContentDescription("Enter telephone number"),
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
        appCompatEditText7.perform(replaceText("31625478547"), closeSoftKeyboard())

        onView(withId(R.id.paisSpinner))
            .perform(click())

        onData(allOf(`is`(Matchers.instanceOf(RegistroInfoCandidatoActivity.Pais::class.java)), `is`(
            RegistroInfoCandidatoActivity.Pais("1", "Colombia")
        )))
            .perform(click())

        onView(withId(R.id.departamentoSpinner))
            .perform(click())

        onData(allOf(`is`(Matchers.instanceOf(RegistroInfoCandidatoActivity.Departamento::class.java)), `is`(
            RegistroInfoCandidatoActivity.Departamento("1", "Antioquia")
        )))
            .perform(click())


        onView(withId(R.id.ciudadSpinner))
            .perform(click())

        onData(allOf(`is`(Matchers.instanceOf(RegistroInfoCandidatoActivity.Ciudad::class.java)), `is`(
            RegistroInfoCandidatoActivity.Ciudad("1", "Medellin")
        )))
            .perform(click())
        Thread.sleep(1_000)

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.profesionEditText), withContentDescription("Enter Profession"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    11
                ),
                isDisplayed()
            )
        )
        appCompatEditText8.perform(replaceText("Ingeniero d"), closeSoftKeyboard())
        Thread.sleep(1_000)

        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.experienciaEditText),
                withContentDescription("Enter years of experiences"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    12
                ),
                isDisplayed()
            )
        )
        appCompatEditText9.perform(replaceText("3"), closeSoftKeyboard())
        Thread.sleep(1_000)

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.continuar_regis_per_candidato_button),
                withText("Following"),
                withContentDescription("Button to continue registrationcandidate informattion"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    13
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())
        Thread.sleep(1_000)

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.ultimoEstudioEditText), withContentDescription("Enter Latest Study"),
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
        appCompatEditText10.perform(replaceText("Profesional"), closeSoftKeyboard())

       val appCompatEditText11 = onView(
            allOf(
                withId(R.id.institucionEditText),
                withContentDescription("Enter institution of last study"),
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
        appCompatEditText11.perform(replaceText("Universidad de Antioquia"), closeSoftKeyboard())

        val appCompatEditText12 = onView(
            allOf(
                withId(R.id.anioGradoEditText), withContentDescription("Enter year of degree"),
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
        appCompatEditText12.perform(replaceText("2018"), closeSoftKeyboard())
        onView(withId(R.id.departamentoSpinner))
            .perform(click())

        onData(allOf(`is`(Matchers.instanceOf(RegistroInfoEduCandidatoActivity.Departamento::class.java)), `is`(
            RegistroInfoEduCandidatoActivity.Departamento("1", "Antioquia")
        )))
            .perform(click())


        onView(withId(R.id.ciudadSpinner))
            .perform(click())

        onData(allOf(`is`(Matchers.instanceOf(RegistroInfoEduCandidatoActivity.Ciudad::class.java)), `is`(
            RegistroInfoEduCandidatoActivity.Ciudad("1", "Medellin")
        )))
            .perform(click())
        Thread.sleep(1_000)

        val appCompatEditText13 = onView(
            allOf(
                withId(R.id.cargoEditText),
                withContentDescription("Enter the position of your last job"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        appCompatEditText13.perform(replaceText("Desarrollador"), closeSoftKeyboard())

        val appCompatEditText14 = onView(
            allOf(
                withId(R.id.ultimaEmpresaEditText),
                withContentDescription("Enter the name of the last company"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    9
                ),
                isDisplayed()
            )
        )
        appCompatEditText14.perform(replaceText("Empresa a"), closeSoftKeyboard())

        val appCompatEditText15 = onView(
            allOf(
                withId(R.id.anioIngresoEditText),
                withContentDescription("Enter the year you joined the company"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        appCompatEditText15.perform(replaceText("2019"), closeSoftKeyboard())

        val appCompatEditText16 = onView(
            allOf(
                withId(R.id.anioRetiroEditText), withContentDescription("Enter retirement year"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    11
                ),
                isDisplayed()
            )
        )
        appCompatEditText16.perform(replaceText("2022"), closeSoftKeyboard())
        Thread.sleep(1_000)

        val appCompatButton4 = onView(
            allOf(
                withId(R.id.continuar_regis_lab_candidato_button),
                withText("Following"),
                withContentDescription("Button to continue registrationcandidate informattion"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    12
                ),
                isDisplayed()
            )
        )
        appCompatButton4.perform(click())
        Thread.sleep(1_000)

        val button = onView(
            allOf(
                withId(R.id.agregarPalabraButton),
                withText("+"),
                withContentDescription("Agregar Palabra"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))
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
