(ns MergeSort_Threads
  (:require [clojure.string :as str])
  (:require [clojure.java.io]))

;;Function to gather input from file
;;Slurp reads in string data from file
(defn numbers_list [file]
  (map read-string (str/split-lines (slurp file))))

;;Split code into desired part for number of threads to be ran on
;;For two threads
(defn split-2 [numSeq]
  (partition (/ (count numSeq) 2) numSeq))

;;For four threads
(defn split-4 [numSeq]
  (partition (/ (count numSeq) 4) numSeq))

;;For 8 threads
(defn split-8 [numSeq]
  (partition (/ (count numSeq) 8) numSeq))

;;For 16 threads
(defn split-16 [numSeq]
  (partition (/ (count numSeq) 16) numSeq))

;;For 32 threads
(defn split-32 [numSeq]
  (partition (/ (count numSeq) 32) numSeq))

;;For 64 threads
(defn split-64 [numSeq]
  (partition (/ (count numSeq) 64) numSeq))


;;Merge Sort Algorithm snippets from https://gist.github.com/alco/2135276
(defn merge-seqs
  "Merges two sorted sequences into a single sorted sequence"
  ([left right]
   (merge-seqs (list left right)))
  ([[left right]]
   (loop [l left, r right, result []]
     (let [lhead (first l), rhead (first r)]
       (cond
         (nil? lhead)     (concat result r)
         (nil? rhead)     (concat result l)
         (<= lhead rhead) (recur (rest l) r (conj result lhead))
         true             (recur l (rest r) (conj result rhead)))))))

(defn mergesort
  "Produces a sorted sequence from an input sequence.
  Works best with vectors (since it uses 'count' internally)."
  [xs]
  ((fn mergesort-counted [xs n]
     (if (<= n 1)
       xs
       (let [middle (bit-shift-right n 1)]  ; fast division by 2
         (merge-seqs (map mergesort-counted
                          (split-at middle xs)        ; two halves
                          [middle (- n middle)])))))  ; count of each half
   xs (count xs)))

;;pmap applies parallel execution, since recursion is possible then will happen

(print "All thread variations ran with mergesort and are an average of three executions\n")

(print "\nRunning 1 Thread:\n")
(time (mergesort (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))
(time (mergesort (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))
(time (mergesort (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))

(print "\nRunning 2 Threads:\n")
(time (reduce merge-seqs (pmap mergesort (split-2 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-2 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-2 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))

(print "\nRunning 4 Threads:\n")
(time (reduce merge-seqs (pmap mergesort (split-4 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-4 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-4 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))

(print "\nRunning 8 Threads:\n")
(time (reduce merge-seqs (pmap mergesort (split-8 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-8 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-8 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))

(print "\nRunning 16 Threads:\n")
(time (reduce merge-seqs (pmap mergesort (split-16 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-16 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-16 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))

(print "\nRunning 32 Threads:\n")
(time (reduce merge-seqs (pmap mergesort (split-32 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-32 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-32 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))

(print "\nRunning 64 Threads:\n")
(time (reduce merge-seqs (pmap mergesort (split-64 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-64 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))
(time (reduce merge-seqs (pmap mergesort (split-64 (numbers_list "/Users/daylanquinn/Desktop/untitled/numbers.txt")))))


(System/exit 0)