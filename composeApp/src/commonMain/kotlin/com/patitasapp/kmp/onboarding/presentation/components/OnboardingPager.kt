package com.patitasapp.kmp.onboarding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.patitasapp.kmp.onboarding.presentation.OnboardingPagerInformation
import kotlinx.coroutines.launch
import androidx.compose.material3.Text
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import com.patitasapp.kmp.core.presentation.PatitasButton
import com.patitasapp.kmp.core.presentation.PatitasTitle
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnboardingPager(
    pages: List<OnboardingPagerInformation>,
    modifier: Modifier = Modifier,
    onFinish: () -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.background(Color.White)) {
        HorizontalPager(
            state = pagerState
        ) { indexPage ->
            val information = pages[indexPage]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                PatitasTitle(title = information.title.toString())
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(information.imageResId),
                    contentDescription = "onboarding",
                    modifier = Modifier.aspectRatio(1f),
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.height(90.dp))
                Text(
                    information.subtitle.toString().uppercase(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    ), textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (pagerState.currentPage == pages.lastIndex) {
                PatitasButton(
                    "get_started",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    onFinish()
                }
            } else {
                TextButton(
                    onClick = onFinish, modifier = Modifier
                        .size(width = 90.dp, height = 34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFCAD2C5))
                ) {
                    Text(
                        "skip",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                PatitasPagerIndicator(
                    pageCount = pages.size,
                    currentPage = pagerState.currentPage,
                    activeColor = MaterialTheme.colorScheme.tertiary,
                    inactiveColor = MaterialTheme.colorScheme.primary
                )

                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage < pages.lastIndex) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    modifier = Modifier
                        .size(width = 90.dp, height = 34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF52796F)),
                ) {
                    Text(
                        "next",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}