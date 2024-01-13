-------------------------------------------------------------------------------
-- Demos for Maxiphobic Heaps
--
-- Data Structures. Grado en Informática. UMA.
-- Pepe Gallardo, 2012
-------------------------------------------------------------------------------

module Demos.Heap.MaxiphobicHeapDemo where

import Data.List(nub)
import DataStructures.Heap.MaxiphobicHeap
import DataStructures.Util.Random
import DataStructures.Graphics.DrawTrees

drawHeap :: (Show a) => Heap a -> IO ()
drawHeap h = drawOn "MaxiphobicHeap.svg" h

outlineHeap :: Heap a -> IO ()
outlineHeap h = outlineOn "MaxiphobicHeap.svg" h

drawCharHeap :: String -> IO ()
drawCharHeap xs = drawOnWith "MaxiphobicHeap.svg" (\k -> [k]) (mkHeap xs)

randomHeap :: Int -> Seed -> Heap Int
randomHeap sz seed = mkHeap (take sz . nub . randoms $ seed)

demo1 sz seed = outlineHeap (randomHeap sz seed)

demo2 xs = drawHeap (mkHeap xs)

demo3 = drawCharHeap "murcielago"
