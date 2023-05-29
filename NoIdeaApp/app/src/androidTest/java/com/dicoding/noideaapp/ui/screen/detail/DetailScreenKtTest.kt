package com.dicoding.noideaapp.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.dicoding.noideaapp.R
import com.dicoding.noideaapp.model.PowerRangers
import com.dicoding.noideaapp.ui.theme.NoIdeaAppTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakePowerRangers = PowerRangers(
        "dino-1",
        "Red Dino Ranger",
        "Dino Thunder",
        "Red",
        "Conner McKnight",
        "https://static.wikia.nocookie.net/powerrangers/images/a/a2/Red_Dino_Ranger_%26_AbaRed.png/revision/latest/scale-to-width-down/1000?cb=20230528152057",
        "Conner McKnight is the Red Dino Ranger and leader of the Dino Rangers.\n\nRetroactively, he is also referred to as the Dino Thunder Red Ranger or Red Dino Thunder Ranger though these are in reference to the show, as opposed to proper labels. Conner has an identical twin brother named Eric McKnight (also played by James Napier) who was once a student at the Wind Ninja Academy, but later flunked out."
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NoIdeaAppTheme {
                DetailContent(
                    photoUrl = fakePowerRangers.photoUrl,
                    title = fakePowerRangers.name,
                    description = fakePowerRangers.description,
                    squadName = fakePowerRangers.squadName,
                    color = fakePowerRangers.color,
                    role = fakePowerRangers.role,
                    onBackClick = { }
                )
            }
        }

        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakePowerRangers.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.rangerSquad,
                fakePowerRangers.squadName
            )
        ).assertIsDisplayed()
    }
}