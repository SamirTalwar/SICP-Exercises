(ns sicp.chapter-2-1.exercise-03
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(defprotocol Shape
  (perimeter [this])
  (area [this]))

(defrecord Rectangle [top right bottom left]
  Shape
  (perimeter [this]
    (def width (- right left))
    (def height (- top bottom))
    (+ width height width height))
  (area [this]
    (def width (- right left))
    (def height (- top bottom))
    (* width height)))

(deftest exercise-03
         (def rectangle-a (Rectangle. 10 10 5 5))
         (is (= 20 (.perimeter rectangle-a)))
         (is (= 25 (.area rectangle-a)))

         (def rectangle-b (Rectangle. 2 6 -9 -3))
         (is (= 40 (.perimeter rectangle-b)))
         (is (= 99 (.area rectangle-b))))
