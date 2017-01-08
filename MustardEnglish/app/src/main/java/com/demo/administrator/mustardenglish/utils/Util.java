package com.demo.administrator.mustardenglish.utils;

import com.demo.administrator.mustardenglish.common.Common;

/**
 * Created by Administrator on 2017/1/8.
 */

public class Util {

      /**
     15      * get postfix of the path
     16      * @param path
     17      * @return
     18      */
                public static String getPostfix(String path) {
               if (path == null || Common.EMPTY.equals(path.trim())) {
                       return Common.EMPTY;
                   }
                if (path.contains(Common.POINT)) {
                         return path.substring(path.lastIndexOf(Common.POINT) + 1, path.length());
                    }
                return Common.EMPTY;
             }
}
