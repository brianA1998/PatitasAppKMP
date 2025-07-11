package com.patitasapp.kmp.onboarding.presentation

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import patitasappkmp.composeapp.generated.resources.Res
import patitasappkmp.composeapp.generated.resources.loginbackground


@OptIn(ExperimentalResourceApi::class)
@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
//    viewModel: OnboardingViewModel = OnboardingViewModel()
) {
    // Check if the user has already seen the onboarding
//    if (viewModel.hasSeenOnboarding) {
//        onFinish()
//        return
//    }

    // Define the onboarding pages
    val pages = listOf(
        OnboardingPagerInformation(
            title = "Welcome to Patitas App",
            subtitle = "Your companion for pet care",
            imageResId = Res.drawable.loginbackground
        ),
        OnboardingPagerInformation(
            title = "Track Your Pet's Health",
            subtitle = "Keep records of vaccinations and vet visits",
            imageResId = Res.drawable.onboarding2
        ),
        OnboardingPagerInformation(
            title = "Connect with Other Pet Owners",
            subtitle = "Share experiences and tips with the community",
            imageResId = Res.drawable.onboarding3
        )
    )

    // Display the onboarding pager
    OnboardingPager(pages = pages, onFinish = {
//        viewModel.completeOnboarding()
}