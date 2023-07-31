package com.andreisingeleytsev.myhabbitsapp.ui.screens.add_habits_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.myhabbitsapp.R
import com.andreisingeleytsev.myhabbitsapp.ui.theme.MainColor
import com.andreisingeleytsev.myhabbitsapp.ui.utils.Fonts
import com.andreisingeleytsev.myhabbitsapp.ui.utils.UIEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitsScreen(paddingValues: PaddingValues, navHostController: NavHostController, viewModel: AddHabitsScreenViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{
            when(it) {
                is UIEvent.Navigate -> {
                    navHostController.navigate(it.route)
                }
                else -> {}
            }
        }
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
            .padding(paddingValues = paddingValues)
    ) {
        val width = maxWidth
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f)) {

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                OutlinedTextField(value = viewModel.name.value, onValueChange = {
                    viewModel.name.value = it
                    viewModel.borderColor.value = Color.White
                },  modifier = Modifier.padding(top = 45.dp), placeholder = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Name of Activity",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White.copy(alpha = 0.44f)
                            )
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = Fonts.customFontFamily,
                        textAlign = TextAlign.Center
                    ),
                    shape = RoundedCornerShape(37.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = viewModel.borderColor.value,
                        unfocusedIndicatorColor = viewModel.borderColor.value
                    ),singleLine = true
                )
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color.White)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(MainColor),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var this_text by remember {
                            mutableStateOf("Category")
                        }
                        var expanded by remember { mutableStateOf(false) }
                        Text(
                            text = this_text,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontFamily = Fonts.customFontFamily
                            ),
                            modifier = Modifier.padding(10.dp)
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .width(width)
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(37.dp)
                                )
                                .background(
                                    MainColor,
                                )
                            ,
                            offset = DpOffset(x = (-24).dp, y = 0.dp)
                        ) {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp)
                                    .background(
                                        MainColor
                                    )) {
                                DropdownMenuItem(
                                    text = { Text("Physical training",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            fontFamily = Fonts.customFontFamily
                                        ))  },
                                    onClick = { this_text = "Physical training"
                                        expanded = false}
                                )
                                DropdownMenuItem(
                                    text = { Text("Workout",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            fontFamily = Fonts.customFontFamily
                                        )) },
                                    onClick = { this_text = "Workout"
                                        expanded = false }
                                )
                                DropdownMenuItem(
                                    text = { Text("Professional Sports",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            fontFamily = Fonts.customFontFamily
                                        )) },
                                    onClick = { this_text = "Professional Sports"
                                        expanded = false }
                                )
                                DropdownMenuItem(
                                    text = { Text("Healthy eating",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            fontFamily = Fonts.customFontFamily
                                        )) },
                                    onClick = { this_text = "Healthy eating"
                                        expanded = false }
                                )
                                DropdownMenuItem(
                                    text = { Text("Sleeping",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            fontFamily = Fonts.customFontFamily
                                        )) },
                                    onClick = { this_text = "Sleeping"
                                        expanded = false }
                                )
                                DropdownMenuItem(
                                    text = { Text("Water",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            fontFamily = Fonts.customFontFamily
                                        )) },
                                    onClick = { this_text = "Water"
                                        expanded = false }
                                )
                            }

                        }
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                modifier = Modifier.size(40.dp),
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }

                    }
                }
            }
            Button(onClick = {
                             viewModel.onEvent(AddHabitsScreenEvent.OnMainButtonPressed)
            }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red, contentColor = Color.White
            )) {

                Text(text = "Next",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = Fonts.customFontFamily
                    ),
                modifier = Modifier.padding(10.dp))
            }
        }
        
        

    }

}