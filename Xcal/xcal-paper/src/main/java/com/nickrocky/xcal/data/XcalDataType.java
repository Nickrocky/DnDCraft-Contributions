package com.nickrocky.xcal.data;

import lombok.SneakyThrows;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class XcalDataType<T extends ConfigurationSerializable> implements PersistentDataType<byte[], T> {

    private final Class<T> dataType;

    public XcalDataType(Class<T> dataType){
        this.dataType = dataType;
    }

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<T> getComplexType() {
        return dataType;
    }

    @SneakyThrows
    @Override
    public @NotNull byte[] toPrimitive(@NotNull T t, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(outputStream);
        bukkitObjectOutputStream.writeObject(t);
        return outputStream.toByteArray();
    }

    @SneakyThrows
    @Override
    public @NotNull T fromPrimitive(byte @NotNull [] bytes, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(inputStream);
        return (T) bukkitObjectInputStream.readObject();
    }

}
