package com.company;

import com.Product.Product;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ReadWriteFile<T> {
    public List<T> readDataFromFile(String path) {
        List<T> list = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();
            fis.close();
            ois.close();
        } catch (EOFException e){
            System.err.println();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void writeDataToFile(String path, List<T> list){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            fos.close();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void writeMapToFile(String path, Map<T,T> map){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            fos.close();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Map<T,T> readMapFromFile(String path) {
        Map<T,T> map = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (Map<T, T>) ois.readObject();
            fis.close();
            ois.close();
        } catch (EOFException e){
            System.err.println();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }
}
