package com.busekylin.codebyte.proxy;

import java.util.Arrays;
import java.util.List;

public class MemoryDatabase {
    public List<String> load(String info) {
        System.out.println("数据库访问");
        return Arrays.asList(info + ": foo", info + ": bar");
    }
}
