(ns shortener.util
  (:require [shortener.db :as db])
  (:require [ring.util.response :as response]))


(def hex-bin-map
  {:0 "0000", :1 "0001", :2 "0010", :3 "0011" :4 "0100", :5 "0101" :6 "0110", :7 "0111" :8 "1000", :9 "1001" :a "1010", :b "1011" :c "1100", :d "1101" :e "1110", :f "1111"})

(def base-64-map
  { 0 "A",     17 "R" ,    34 "i",     51 "z",
1 "B" ,    18 "S"   ,  35 "j"  ,   52 "0",
2 "C"  ,   19 "T"   ,  36 "k"  ,   53 "1",
3 "D"   ,  20 "U"   ,  37 "l"  ,   54 "2",
4 "E"    , 21 "V"   ,  38 "m"  ,   55 "3",
5 "F"    , 22 "W"   ,  39 "n"  ,   56 "4",
6 "G"     ,23 "X"   ,  40 "o"  ,   57 "5",
7 "H"     ,24 "Y"   ,  41 "p"  ,   58 "6",
8 "I"    , 25 "Z"   ,  42 "q"  ,   59 "7",
9 "J"    , 26 "a"   ,  43 "r"  ,   60 "8",
10 "K"   , 27 "b"   ,  44 "s"   ,  61 "9",
11 "L"  ,  28 "c"   ,  45 "t"  ,   62 "+",
12 "M"  ,  29 "d"   ,  46 "u"  ,   63 "/",
13 "N"   , 30 "e"   ,  47 "v",
14 "O"  ,  31 "f"  ,   48 "w",
15 "P"  ,  32 "g" ,    49 "x",
16 "Q"   , 33 "h",     50 "y"})

(defn sha1-str [s]
  (->> (-> "sha1"
           java.security.MessageDigest/getInstance
           (.digest (.getBytes s)))
       (map #(.substring
              (Integer/toString
               (+ (bit-and % 0xff) 0x100) 16) 1))
       (apply str)))

(defn base-64-encode [grouped-bin]
  (map #(apply str (map (fn [x] (get base-64-map (eval (read-string (str "2r" x))))) %)) (map (partial apply str) (partition 6 grouped-bin))))

(defn encode-url [url]
  (let [sha1-bin-seq (apply str (map #((keyword (str %)) hex-bin-map) (sha1-str url)))]
    (let [bin-groups (map #(get base-64-map (eval (read-string (str "2r" %)))) (map (partial apply str) (partition 6 sha1-bin-seq)))]
      (apply str bin-groups))))

(defn shorten [url length]
  (let [encoded (encode-url url)]
    (let [substring (if (= 8 length)
                      (subs encoded 0 8)
                      (subs encoded 0 6))
          map {:original url :generated substring}]
      (do
        (db/insert-record map)
        map))))
    
      
(defn retrieve-url [generated]
  (let [og (db/find-doc generated)]
    (dissoc og :_id)))

    