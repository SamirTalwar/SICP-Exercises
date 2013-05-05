(ns sicp.chapter-2-1.exercise-14
  (:use clojure.test)
  (:require [clojure.contrib.generic.arithmetic :refer [+ - * /]]))

(defrecord Interval [lower upper])

(defmethod + [Interval Interval]
  [x y]
  (Interval/create {:lower (+ (:lower x) (:lower y))
                    :upper (+ (:upper x) (:upper y))}))

(defmethod * [Interval Interval]
  [x y]
  (let [p1 (* (:lower x) (:lower y))
        p2 (* (:lower x) (:upper y))
        p3 (* (:upper x) (:lower y))
        p4 (* (:upper x) (:upper y))]
    (Interval/create {:lower (min p1 p2 p3 p4)
                      :upper (max p1 p2 p3 p4)})))

(defmethod / [Interval Interval]
  [x y]
  (when (or (< (:lower x) 0 (:upper x))
            (< (:lower y) 0 (:upper y)))
    (throw (ArithmeticException. "Interval spans zero")))
  (* x
     (Interval/create {:lower (/ (:upper y))
                       :upper (/ (:lower y))})))

(defn par1 [interval-a interval-b]
  (/ (* interval-a interval-b)
     (+ interval-a interval-b)))

(defn par2 [interval-a interval-b]
  (let [one (Interval/create {:lower 1, :upper 1})]
    (/ one
       (+ (/ one interval-a)
          (/ one interval-b)))))

(deftest exercise-14
         (let [a (Interval/create {:lower 99, :upper 101})
               b (Interval/create {:lower 150, :upper 160})]
           (is (not (= (par1 a a) (par2 a a))))
           (is (not (= (par1 b b) (par2 b b))))
           (is (not (= (par1 a b) (par2 a b))))))

; user=> (def a (Interval/create {:lower 99, :upper 101}))
; #'user/a
; user=> (def b (Interval/create {:lower 150, :upper 160}))
; #'user/b
; user=> (/ a a)
; #user.Interval{:lower 99/101, :upper 101/99}
; user=> (/ b b)
; #user.Interval{:lower 15/16, :upper 16/15}
; user=> (par1 a a)
; #user.Interval{:lower 9801/202, :upper 10201/198}
; user=> (par2 a a)
; #user.Interval{:lower 99/2, :upper 101/2}
; user=> (par1 a b)
; #user.Interval{:lower 1650/29, :upper 16160/249}
; user=> (par2 a b)
; #user.Interval{:lower 4950/83, :upper 16160/261}

; Running all this shows us that `par1` and `par2` result in different values.
