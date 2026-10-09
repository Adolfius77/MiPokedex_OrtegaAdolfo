package ortega.adolfo.mipokedex_ortegaadolfo.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberChip(
    texto: String,
    modifier: Modifier = Modifier,
    colores: Pair<Color, Color>,
    borderColor: Color = Color.Black,
    borderWidth: androidx.compose.ui.unit.Dp = 1.dp
) {
    val (fondo, colorTexto) = colores

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .size(30.dp)
            .background(color = fondo, shape = CircleShape)
            .border(width = borderWidth, color = borderColor, shape = CircleShape)
            .padding(5.dp)
    ) {
        Text(
            text = texto,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Black,
            color = colorTexto
        )
    }
}

@Composable
fun numberChip(
    texto: String,
    modifier: Modifier = Modifier,
    colores: Pair<Color, Color>
) {
    NumberChip(texto = texto, modifier = modifier, colores = colores)
}

@Preview
@Composable
private fun NumberChipPreview() {
    NumberChip(
        texto = "25",
        colores = Pair(Color.Green, Color.White)
    )
}
