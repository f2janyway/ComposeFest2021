package com.box.layoutcompose_f2j

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.box.layoutcompose_f2j.ui.theme.LayoutCompose_f2jTheme

@Composable
fun ConstComposable(modifier: Modifier = Modifier) {

    ConstraintLayout {
        val textValue = remember {
            mutableStateOf("")
        }
        val (text, button, textField) = createRefs()

        val guideline = createGuidelineFromStart(0.1f)
        val middleGuideline = createGuidelineFromAbsoluteLeft(0.5f)
        Text(
            text = "Title veryveryveryveryveryveryveryveryveryveryve123456789123456789 12345 ",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top, margin = 10.dp)
                end.linkTo(middleGuideline)
                bottom.linkTo(parent.bottom, margin = 10.dp)
//                width = Dimension.preferredWrapContent
            },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis

        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button) {
                start.linkTo(guideline, margin = 25.dp)
                top.linkTo(textField.bottom, margin = 5.dp)
            },
        ) {
            Column {
                Text("Hello")
                Text("Hello")
                Text("Hello")
            }
        }


        TextField(
            value = textValue.value,
            onValueChange = {
                textValue.value = it
            },
            modifier = Modifier.constrainAs(textField) {
                top.linkTo(text.top)
                bottom.linkTo(text.bottom)
                start.linkTo(text.end, margin = 20.dp)
                end.linkTo(parent.end, margin = 5.dp)
            }
        )


    }
}

@Composable
fun TwoTexts(modifier: Modifier = Modifier, startText: String, endText: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = startText
        )

        Divider(
            color = Color.Black, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),

            text = endText
        )
    }
}

@Composable
@Preview
fun TwoTextsPreview() {
    LayoutCompose_f2jTheme {
//        TwoTexts(startText = "HI", endText = "Buy")
        ConstComposable()
    }
}