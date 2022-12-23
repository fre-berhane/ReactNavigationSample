import React from "react";
import { StyleSheet, View, Button,TextInput } from "react-native";
import Title from "../components/Title";

export default function HelloWorld() {
  return (
    <View style={styles.container}>
      <Title title="Hello World" />
      <View style={styles.view_container}>
        <Button title="RN Test"/>
        <TextInput 
               underlineColorAndroid = "transparent"
               placeholder = "Placeholder"
               placeholderTextColor = "#9a73ef"styles={styles.input}/>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    backgroundColor: "#1d1d1d",
  },
  view_container:{
    margin: 20
  },
  input:{
    marginTop: 20,
    paddingLeft: 10,
    paddingRight: 10,
    backgroundColor: "#fff",
    borderColor:'#fff',
    borderWidth: 1
  }
});

/*
alignItems: "center",
flex: 1,
flexDirection: "column",
height: "100%",
justifyContent: "space-between",
width: "100%",
*/
