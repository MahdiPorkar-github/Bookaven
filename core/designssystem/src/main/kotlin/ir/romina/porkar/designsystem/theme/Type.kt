package ir.romina.porkar.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontSize = 25.sp,
        color = Black,
        fontWeight = FontWeight.Bold,
    ),

    titleMedium = TextStyle(
        fontSize = 20.sp,
        color = Black,
        fontWeight = FontWeight.Medium,
    ),

    titleSmall = TextStyle(
        fontSize = 15.sp,
        color = WhiteBackground,
        fontWeight = FontWeight.Bold,
    ),

    labelSmall = TextStyle(
        fontSize = 15.sp,
        color = Black,
        fontWeight = FontWeight.Bold,
    ),

)