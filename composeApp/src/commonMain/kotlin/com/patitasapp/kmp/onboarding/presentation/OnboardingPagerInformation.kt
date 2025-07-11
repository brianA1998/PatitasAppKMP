package com.patitasapp.kmp.onboarding.presentation

import org.jetbrains.compose.resources.DrawableResource

/**
 * Data class that contains the information for the onboarding pager.
 *
 * @property title The title of the onboarding page.
 * @property subtitle The subtitle of the onboarding page.
 * @property imageResId The drawable resource ID for the image to be displayed on the page.
 */
data class OnboardingPagerInformation(
    val title: String,
    val subtitle: String,
    val imageResId: DrawableResource
)
