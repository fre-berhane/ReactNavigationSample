import React from "react";
import { StyleSheet, View, Button,TextInput } from "react-native";
import Title from "../components/Title";

export default function HelloWorld() {
  return (
    <View style={styles.container}>
      <Title title="Hello World" />
      <View style={styles.view_container}>
        <Button title="RN Test"/>
        <TextInput styles={styles.input}/>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    backgroundColor: "transparent",
  },
  view_container:{
    margin: 20
  },
  input:{
    marginTop: 20,
    paddingLeft: 10,
    paddingRight: 10,
    backgroundColor: "#fff"
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
