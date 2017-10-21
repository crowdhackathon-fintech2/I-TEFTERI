//package com.java.main.i_tefteri;
//
//import java.io.Serializable;
//
//public class NameValuePair<V> implements Serializable {
//     private static final long serialVersionUID = 20070523L;
//   /** The name part. */
//        public String name;
//        public V value;
//  public NameValuePair(String name, V value) {
//this.name = name;
//this.value = value;
//}
//
//
///**
// 54      * Constructs an empty NameValuePair.
// 55      */
// public NameValuePair() {
// }
//
//
///**
// 60      * A NameValuePair equals another if and only if both the name and value
// 61      * equal.
// 62      * @param o The other NameValuePair
// 63      * @return True if the o equals this object, false otherwise
// 64      */
//public boolean equals(Object o) {
//if (this == o)
//return true;
//if (!(o instanceof NameValuePair))
//return false;
//final NameValuePair nameValuePair = (NameValuePair) o;
//   if (name != null ? !name.equals(nameValuePair.name) :
//nameValuePair.name != null)
//return false;
//if (value != null ? !value.equals(nameValuePair.value) :
//nameValuePair.value != null)
//return false;
//
//
//return true;
//}
//
// /**
// 84      * Obtains the hash code of this NameValuePair.
// 85      * @return The hash code
// 86      */
//public int hashCode() {
//int result;
//result = (name != null ? name.hashCode() : 0);
//result = 29 * result + (value != null ? value.hashCode() : 0);
//return result;
//}
//}
