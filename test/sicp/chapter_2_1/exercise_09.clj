(ns sicp.chapter-2-1.exercise-09
  (:use clojure.test)
  (:require [clojure.contrib.generic.arithmetic :as arithmetic]))

(defprotocol Interval
  (lower [this])
  (upper [this])
  (width [this]))

(defrecord NumericInterval [lower upper]
  Interval
  (width [this]
    (/ (- upper lower) 2)))

(deftest exercise-09
         (is (= 3 (.width (NumericInterval/create {:lower 3, :upper 9}))))
         (is (= 15/2 (.width (NumericInterval/create {:lower -4, :upper 11})))))

;;;;;

(defmethod arithmetic/+ [Interval Interval]
  [x y]
  (NumericInterval/create {:lower (arithmetic/+ (lower x) (lower y))
                           :upper (arithmetic/+ (upper x) (upper y))}))

(defmethod arithmetic/* [Interval Interval]
  [x y]
  (let [p1 (* (lower x) (lower y))
        p2 (* (lower x) (upper y))
        p3 (* (upper x) (lower y))
        p4 (* (upper x) (upper y))]
    (NumericInterval/create {:lower (min p1 p2 p3 p4)
                             :upper (max p1 p2 p3 p4)})))

; Proof the width of the sum of two intervals is a function only of the widths being added:
;
; (def a (NumericInterval/create {:lower aL, :upper aU}))
; (def b (NumericInterval/create {:lower bL, :upper bU}))
; (.width (arithmetic/+ a b))
; = (.width (NumericInterval/create {:lower (+ aL bL), :upper (+ aU bU)}))
; = (/ (- (+ aU bU) (+ aL bL)) 2)
; = (/ (+ (- aU aL) (- bU bL)) 2)
; = (+ ((- aU aL) / 2) ((- bU bL) / 2))
; = (+ (.width a) (.width b))
;
; The width of the product of two intervals is a function of the lower and upper bounds of both intervals,
; and cannot be simplified to just their widths.
;
; For example:
;
; (def a (NumericInterval/create {:lower -3, :upper 2}))
; (def b (NumericInterval/create {:lower -4, :upper 8}))
;
; (.width a)
; = 2.5
;
; (.width b)
; = 6
;
; (.width (arithmetic/* a b))
; = (.width (NumericInterval/create {:lower (* -3 8), :upper (* 2 8)}))
; = (.width (NumericInterval/create {:lower -24, :upper 16}))
; = (/ (- 16 -24) 2)
; = 20
