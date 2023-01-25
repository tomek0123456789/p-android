package com.example.zad5_shopping_app


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.zad5_shopping_app.ui.shopping_cart.ShoppingCartListAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UITests {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun noItemsAdded() {
        val bottomNavigationItemView = onView(
            allOf(withId(R.id.navigation_shopping_cart), withContentDescription("Cart"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation_view),
                        0),
                    2),
                isDisplayed()))
        bottomNavigationItemView.perform(click())
        val textView2 = onView(
            allOf(withId(R.id.price_view),
                withText("Total: $0.0"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                ))
        textView2.check(matches(isDisplayed()))       //1
    }

    @Test
    fun addOneItem() {
        val materialButton = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.products_recycler_view),
                        0),
                    2),
                isDisplayed()))
        materialButton.perform(click())

        val bottomNavigationItemView = onView(
            allOf(withId(R.id.navigation_shopping_cart), withContentDescription("Cart"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation_view),
                        0),
                    2),
                isDisplayed()))
        bottomNavigationItemView.perform(click())

        val textView = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(withParent(withId(R.id.shopping_cart_recycler_view))),
                ))
        textView.check(matches(isDisplayed()))              //2

        val textView2 = onView(
            allOf(withId(R.id.price_view), withText("Total: $1.0"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                ))
        textView2.check(matches(isDisplayed()))   //3

        val textView3 = onView(
            allOf(withId(R.id.product_name_cart), withText("a"),
                withParent(withParent(withId(R.id.shopping_cart_recycler_view))),
                ))
        textView3.check(matches(isDisplayed()))             //4

        val textView4 = onView(
            allOf(withText("Shopping cart"),
                withParent(allOf(withId(androidx.constraintlayout.widget.R.id.action_bar),
                    withParent(withId(androidx.constraintlayout.widget.R.id.action_bar_container)))),
                ))
        textView4.check(matches(isDisplayed())) //5
    }

    @Test
    fun addThreeDifferentItems() {
        val materialButton = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            0)),
                    2),
                isDisplayed()))
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            1)),
                    2),
                isDisplayed()))
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.plus_button),
                withText("+"),
                childAtPosition(
                    allOf(
                        withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            2)
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val bottomNavigationItemView = onView(
            allOf(withId(R.id.navigation_shopping_cart), withContentDescription("Cart"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation_view),
                        0),
                    2),
                isDisplayed()))
        bottomNavigationItemView.perform(click())           //6

        val textView = onView(
            allOf(
                withId(R.id.product_name_cart),
                withText("a"),
                withParent(
                    allOf(
                        withId(R.id.shopping_cart_item_layout),
                        withParent(
                            withId(R.id.shopping_cart_recycler_view)
                        )
                    )
                ),
            )
        )
        textView.check(matches(isDisplayed()))          //7

        val textView2 = onView(
            allOf(
                withId(R.id.quantity_cart),
                withText("1"),
                withParent(
                    allOf(
                        withId(R.id.shopping_cart_item_layout),
                        withParent(
                            withId(R.id.shopping_cart_recycler_view)
                        ),
                        withChild(
                            allOf(
                                withId(R.id.product_name_cart),
                                withText("a")
                            )
                        )
                    )
                ),

            )
        )
        textView2.check(matches(isDisplayed()))         //8

        val textView3 = onView(
            allOf(
                withId(R.id.product_name_cart),
                withText("b"),
                withParent(
                    allOf(
                        withId(R.id.shopping_cart_item_layout),
                        withParent(
                            withId(R.id.shopping_cart_recycler_view)
                        )
                    )
                ),

            )
        )
        textView3.check(matches(isDisplayed()))         //9

        val textView4 = onView(
            allOf(
                withId(R.id.quantity_cart),
                withText("1"),
                withParent(
                    allOf(
                        withId(R.id.shopping_cart_item_layout),
                        withParent(
                            withId(R.id.shopping_cart_recycler_view)
                        ),
                        withChild(
                            allOf(
                                withId(R.id.product_name_cart),
                                withText("b")
                            )
                        )
                    )
                ),
            )
        )
        textView4.check(matches(isDisplayed()))         //10

        val textView5 = onView(
            allOf(withId(R.id.product_name_cart), withText("c"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                ))
        textView5.check(matches(isDisplayed()))         //11

        val textView6 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)), withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("a")
                        )
                    ))),
                ))
        textView6.check(matches(isDisplayed()))         //12

        val textView7 = onView(
            allOf(withId(R.id.price_view),
                withText("Total: $6.0"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                ))
        textView7.check(matches(isDisplayed()))   //13
    }

    @Test
    fun addTwoSameItems() {
        val materialButton = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            0)),
                    2),
                isDisplayed()))
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            0)),
                    2),
                isDisplayed()))
        materialButton2.perform(click())

        val bottomNavigationItemView = onView(
            allOf(withId(R.id.navigation_shopping_cart), withContentDescription("Cart"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation_view),
                        0),
                    2),
                isDisplayed()))
        bottomNavigationItemView.perform(click())

        val textView = onView(
            allOf(withId(R.id.product_name_cart), withText("a"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                ))
        textView.check(matches(isDisplayed()))          //14

        val textView2 = onView(
            allOf(withId(R.id.quantity_cart), withText("2"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                ))
        textView2.check(matches(isDisplayed()))         //15

        val textView3 = onView(
            allOf(withId(R.id.price_view), withText("Total: $2.0"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                ))
        textView3.check(matches(isDisplayed()))         //16
    }

    @Test
    fun addTwoSameItemsThenRemoveOne() {
        val materialButton = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            0)),
                    2),
                isDisplayed()))
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            0)),
                    2),
                isDisplayed()))
        materialButton2.perform(click())

        val bottomNavigationItemView = onView(
            allOf(withId(R.id.navigation_shopping_cart), withContentDescription("Cart"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation_view),
                        0),
                    2),
                isDisplayed()))
        bottomNavigationItemView.perform(click())

        val textView = onView(
            allOf(withId(R.id.product_name_cart), withText("a"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
            ))
        textView.check(matches(isDisplayed()))          //17

        val textView2 = onView(
            allOf(withId(R.id.quantity_cart), withText("2"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
            ))
        textView2.check(matches(isDisplayed()))         //18

        val textView3 = onView(
            allOf(withId(R.id.price_view), withText("Total: $2.0"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
            ))
        textView3.check(matches(isDisplayed()))         //19

        val materialButton3 = onView(
            allOf(withId(R.id.minus_button), withText("-"),
                childAtPosition(
                    allOf(withId(R.id.shopping_cart_item_layout),
                        childAtPosition(
                            withId(R.id.shopping_cart_recycler_view),
                            0)),
                    3),
                isDisplayed()))
        materialButton3.perform(click())

        val textView4 = onView(
            allOf(withId(R.id.product_name_cart),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView4.check(matches(withText("a")))         //20

        val textView5 = onView(
            allOf(withId(R.id.quantity_cart),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView5.check(matches(withText("1")))         //21

        val textView6 = onView(
            allOf(withId(R.id.price_view),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()))
        textView6.check(matches(withText("Total: $1.0")))           //22
    }

    @Test
    fun addOneItemThenRemoveIt() {
        val materialButton = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            0)),
                    2),
                isDisplayed()))
        materialButton.perform(click())

        val bottomNavigationItemView = onView(
            allOf(withId(R.id.navigation_shopping_cart), withContentDescription("Cart"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation_view),
                        0),
                    2),
                isDisplayed()))
        bottomNavigationItemView.perform(click())

        val textView = onView(
            allOf(withId(R.id.product_name_cart),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView.check(matches(withText("a")))          //23

        val textView2 = onView(
            allOf(withId(R.id.quantity_cart),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView2.check(matches(withText("1")))         //24

        val textView3 = onView(
            allOf(withId(R.id.price_view),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()))
        textView3.check(matches(withText("Total: $1.0")))           //25

        val materialButton2 = onView(
            allOf(withId(R.id.minus_button), withText("-"),
                childAtPosition(
                    allOf(withId(R.id.shopping_cart_item_layout),
                        childAtPosition(
                            withId(R.id.shopping_cart_recycler_view),
                            0)),
                    3),
                isDisplayed()))
        materialButton2.perform(click())

        val textView4 = onView(
            allOf(withId(R.id.price_view),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()))
        textView4.check(matches(withText("Total: $0.0"))) //26

        val item = onView(
            allOf(
                withId(R.id.shopping_cart_item_layout),
                withParent(withId(R.id.shopping_cart_recycler_view))
                )
            )
        item.check(doesNotExist())          //27
    }

    @Test
    fun addAllItems() {
        val materialButton = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            0)),
                    2),
                isDisplayed()))
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            1)),
                    2),
                isDisplayed()))
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            2)),
                    2),
                isDisplayed()))
        materialButton3.perform(click())

        val materialButton4 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            3)),
                    2),
                isDisplayed()))
        materialButton4.perform(click())

        val materialButton5 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            4)),
                    2),
                isDisplayed()))
        materialButton5.perform(click())

        val materialButton6 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            5)),
                    2),
                isDisplayed()))
        materialButton6.perform(click())

        val materialButton7 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            6)),
                    2),
                isDisplayed()))
        materialButton7.perform(click())

        val materialButton8 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            7)),
                    2),
                isDisplayed()))
        materialButton8.perform(click())

        val materialButton9 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            8)),
                    2),
                isDisplayed()))
        materialButton9.perform(click())

        val materialButton10 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            9)),
                    2),
                isDisplayed()))
        materialButton10.perform(click())

        val materialButton11 = onView(
            allOf(withId(R.id.plus_button), withText("+"),
                childAtPosition(
                    allOf(withId(R.id.product_item_layout),
                        childAtPosition(
                            withId(R.id.products_recycler_view),
                            10)),
                    2),
                isDisplayed()))
        materialButton11.perform(click())

        val bottomNavigationItemView = onView(
            allOf(withId(R.id.navigation_shopping_cart), withContentDescription("Cart"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation_view),
                        0),
                    2),
                isDisplayed()))
        bottomNavigationItemView.perform(click())

        val textView = onView(
            allOf(withId(R.id.product_name_cart), withText("a"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView.check(matches(withText("a"))) //28

        val textView2 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)), withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("a")
                        )
                    ))),
                isDisplayed()))
        textView2.check(matches(withText("1"))) //29

        val textView3 = onView(
            allOf(withId(R.id.product_name_cart), withText("b"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView3.check(matches(withText("b"))) //30

        val textView4 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)),withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("b")
                        )
                    ))),
                isDisplayed()))
        textView4.check(matches(withText("1"))) //31

        val textView5 = onView(
            allOf(withId(R.id.product_name_cart), withText("c"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView5.check(matches(withText("c"))) //32

        val textView6 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)),withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("c")
                        )
                    ))),
                isDisplayed()))
        textView6.check(matches(withText("1"))) //33

        val textView7 = onView(
            allOf(withId(R.id.product_name_cart), withText("d"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView7.check(matches(withText("d"))) //34

        val textView8 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)),withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("d")
                        )
                    ))),
                isDisplayed()))
        textView8.check(matches(withText("1"))) //35

        val textView9 = onView(
            allOf(withId(R.id.product_name_cart), withText("e"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView9.check(matches(withText("e"))) //36

        val textView10 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)),withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("e")
                        )
                    ))),
                isDisplayed()))
        textView10.check(matches(withText("1"))) //37

        val textView11 = onView(
            allOf(withId(R.id.product_name_cart), withText("f"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView11.check(matches(withText("f"))) //38

        val textView24 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)), withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("f")
                        )
                    ))),
                isDisplayed()))
        textView24.check(matches(withText("1")))//46

        val textView12 = onView(
            allOf(withId(R.id.product_name_cart), withText("g"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)), )),
                isDisplayed()))
        textView12.check(matches(withText("g")))//39

        val textView13 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)), withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("g")
                        )
                    ))),
                isDisplayed()))
        textView13.check(matches(withText("1")))//40

        onView(withId(R.id.shopping_cart_recycler_view)).perform(
            RecyclerViewActions.scrollToLastPosition<ShoppingCartListAdapter.ShoppingCartItemViewHolder>()
        )
        val textView14 = onView(
            allOf(withId(R.id.product_name_cart), withText("h"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView14.check(matches(withText("h"))) //41


        val textView15 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)), withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("h")
                        )
                    ))),
                isDisplayed()))
        textView15.check(matches(withText("1"))) //42


        val textView16 = onView(
            allOf(withId(R.id.product_name_cart), withText("i"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView16.check(matches(withText("i"))) //43

        val textView17 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)), withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("i")
                        )
                    ))),
                isDisplayed()))
        textView17.check(matches(withText("1"))) //44

        val textView18 = onView(
            allOf(withId(R.id.product_name_cart), withText("j"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView18.check(matches(withText("j"))) //45

        val textView20 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)), withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("j")
                        )
                    ))),
                isDisplayed()))
        textView20.check(matches(withText("1"))) //47

        val textView21 = onView(
            allOf(withId(R.id.product_name_cart), withText("k"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)))),
                isDisplayed()))
        textView21.check(matches(withText("k"))) //48

        val textView22 = onView(
            allOf(withId(R.id.quantity_cart), withText("1"),
                withParent(allOf(withId(R.id.shopping_cart_item_layout),
                    withParent(withId(R.id.shopping_cart_recycler_view)), withChild(
                        allOf(
                            withId(R.id.product_name_cart),
                            withText("k")
                        )
                    ))),
                isDisplayed()))
        textView22.check(matches(withText("1"))) //49

        val textView23 = onView(
            allOf(withId(R.id.price_view), withText("Total: $366.0"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()))
        textView23.check(matches(withText("Total: $366.0"))) //50
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int,
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
