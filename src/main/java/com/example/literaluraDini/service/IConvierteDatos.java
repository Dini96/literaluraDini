package com.example.literaluraDini.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
