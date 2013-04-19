(ns sicp.chapter-1-2.exercise-10
  (:use clojure.test))

(defn A [x y]
  (cond (= y 0) 0
        (= x 0) (* 2 y)
        (= y 1) 2
        :else (A (dec x) (A x (dec y)))))

(defn f [n] (A 0 n))
(defn g [n] (A 1 n))
(defn h [n] (A 2 n))
(defn k [n] (* 5 n n))

(defn pow [a n]
  (letfn [(pow-iter [i n]
            (if (= n 0)
              i
              (pow-iter (* i a) (dec n))))]
    (pow-iter 1 n)))

(defn tetrate [a n]
  (letfn [(tetr-iter [i n]
            (if (= n 0)
              i
              (tetr-iter (pow a i) (dec n))))]
    (tetr-iter 1 n)))

(deftest exercise-10
         (is (= 1024 (A 1 10)))
         (is (= 65536 (A 2 4)))
         (is (= 65536 (A 3 3)))

         ; Redo the following with ClojureCheck

         (is (= (* 2 1) (f 1)))
         (is (= (* 2 2) (f 2)))
         (is (= (* 2 3) (f 3)))
         (is (= (* 2 4) (f 4)))
         (is (= (* 2 5) (f 5)))

         (is (= (pow 2 1) (g 1)))
         (is (= (pow 2 2) (g 2)))
         (is (= (pow 2 3) (g 3)))
         (is (= (pow 2 4) (g 4)))
         (is (= (pow 2 5) (g 5)))

         (is (= (tetrate 2 1) (h 1)))
         (is (= (tetrate 2 2) (h 2)))
         (is (= (tetrate 2 3) (h 3)))
         (is (= (tetrate 2 4) (h 4))))
