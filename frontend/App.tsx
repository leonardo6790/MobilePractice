import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import LoginScreen from './src/screens/LoginScreen';
import HomeScreen from './src/screens/HomeScreen';
import UserScreen from './src/screens/UsersScreen';
import CategoriesScreen from './src/screens/Categories';
import ProductsScreen from './src/screens/ProductsScreen';
import SubCategoriesScreen from './src/screens/SubcategoriesScreen';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';

const Stack = createNativeStackNavigator();
export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Login">
        <Stack.Screen name="Login" 
        component={LoginScreen} 
        options={{ headerShown: false }} 
        />
        <Stack.Screen name="Home" 
        component={HomeScreen} 
        options={{ title: 'Menu principal' }} 
        />
        <Stack.Screen 
        name="Users" 
        component={UserScreen}
        options={{ title: 'Gestion de usuarios' }} 
         />
        <Stack.Screen name="Categories" 
        component={CategoriesScreen}
        options={{ title: 'categorias' }} 
         />
        <Stack.Screen name="Products" 
        component={ProductsScreen} 
        options={{ title: 'Productos' }}
        />
        <Stack.Screen name="SubCategories" 
        component={SubCategoriesScreen} 
        options={{ title: 'Subcategorias' }}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}



