package com.patitasapp.kmp.onboarding.presentation

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import patitasappkmp.composeapp.generated.resources.Res
import patitasappkmp.composeapp.generated.resources.onboarding1
import patitasappkmp.composeapp.generated.resources.onboarding2
import patitasappkmp.composeapp.generated.resources.onboarding3
import patitasappkmp.composeapp.generated.resources.onboarding_subtitle_1
import patitasappkmp.composeapp.generated.resources.onboarding_subtitle_2
import patitasappkmp.composeapp.generated.resources.onboarding_subtitle_3
import patitasappkmp.composeapp.generated.resources.onboarding_title_1
import patitasappkmp.composeapp.generated.resources.onboarding_title_2
import patitasappkmp.composeapp.generated.resources.onboarding_title_3


@OptIn(ExperimentalResourceApi::class)
@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
//    viewModel: OnboardingViewModel = OnboardingViewModel()
) {
//    if (viewModel.hasSeenOnboarding) {
//        onFinish()
//        return
//    }

    // Define the onboarding pages
    val pages = listOf(
        OnboardingPagerInformation(
            title = Res.string.onboarding_title_1,
            subtitle = Res.string.onboarding_subtitle_1,
            imageResId = Res.drawable.onboarding1
        ),
        OnboardingPagerInformation(
            title = Res.string.onboarding_title_2,
            subtitle = Res.string.onboarding_subtitle_2,
            imageResId = Res.drawable.onboarding2
        ),
        OnboardingPagerInformation(
            title = Res.string.onboarding_title_3,
            subtitle = Res.string.onboarding_subtitle_3,
            imageResId = Res.drawable.onboarding3
        )
    )

//    // Display the onboarding pager
//    OnboardingPager(pages = pages, onFinish = {
////        viewModel.completeOnboarding()
//    })
}