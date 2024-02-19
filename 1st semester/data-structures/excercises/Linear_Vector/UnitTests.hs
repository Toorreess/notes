-- Pepe Gallardo

module UnitTests(test, error, runTests) where

import Prelude hiding (error)
import Data.List(intercalate)
import qualified Control.Exception as Exc
import qualified Control.DeepSeq as Deep

test :: (Deep.NFData a, Eq a, Show a) => String -> [a] -> IO Bool
test name [x, y] = Exc.catches (x `Deep.deepseq` noException) [ Exc.Handler errorHandler, Exc.Handler handler ]
    where
        quoted x = "\""++x++"\""

        noException = do
          let ok = x==y
          putStrLn . unwords $ ["Test "++name++":"] ++
            if ok
              then ["OK"]
              else ["FAILED!\n  Expected result is: ",show y,"\n  Obtained result was:",show x]
          return ok

        errorHandler :: Exc.ErrorCall -> IO Bool
        errorHandler (Exc.ErrorCallWithLocation str _) = do
          putStrLn . unwords $ ["Test",name++":","FAILED!\n  Expected result is: ",show y,"\n  Obtained result was: error",quoted str]
          return False

        handler :: Exc.SomeException -> IO Bool
        handler exc = do
          putStrLn . unwords $ ["Test",name++":","FAILED!\n  Expected result is: ",show y,"\n  Obtained result was: ***exception:",show exc]
          return False

error :: (Deep.NFData a) => String -> [a] -> IO Bool
error name [x, err] = do
  msg <- errMsg
  Exc.catches (x `Deep.deepseq` noException msg) [ Exc.Handler $ errorHandler msg, Exc.Handler $ handler msg ]
    where
        quoted x = "\""++x++"\""

        errMsg = Exc.catch (err `Deep.deepseq` return "") $ \(Exc.ErrorCallWithLocation str _) -> return str

        noException msg = do
          putStrLn $ unwords ["Test",name,"FAILED!\n  Expected result is: error",quoted msg,"\n  But didn't raise any error"]
          return False

        errorHandler :: String -> Exc.ErrorCall -> IO Bool
        errorHandler msg (Exc.ErrorCallWithLocation str _) = do
          let ok = str==msg
          putStrLn . unwords $ ["Test",name++":"] ++
            if ok
              then ["OK"]
              else ["FAILED!\n  Should raise: error",quoted msg,"\n  But raised:   error",quoted str]
          return ok

        handler :: String -> Exc.SomeException -> IO Bool
        handler msg exc = do
          putStrLn . unwords $ ["Test",name++":", "FAILED!\n  Should raise: error",quoted msg,"\n  But raised:   ***exception:",show exc]
          return False

runTests :: [(String, [IO Bool])] -> IO ()
runTests tests = do
  marks <- sequence [ run name test | (name, test) <- tests ]
  putStrLn (format marks)
  putStrLn (formatPassed marks)
  putStrLn (excel marks)
  where
    run str test = do
      let title = unwords [ "Tests for", str, "function" ]
      putStrLn title
      putStrLn (replicate (length title) '=')
      bs <- sequence test
      let (passed, total) = count bs
      let tests n = unwords $ show n : if n > 1 then ["tests."] else ["test."]
      let failed = if passed < total then unwords ["Failed", tests (total-passed)] else ""
      putStrLn $ unwords ["\nPassed", show passed, "out of", tests total, failed, "\n\n" ]
      return bs
    excel = intercalate ";;" . map (intercalate ";" . map (\b -> if b then "+" else "-") )
    count bs = (length (filter id bs), length bs)
    format bs = intercalate " " [ show passed++"/"++show total |  (passed, total) <- map count bs ]
    formatPassed bs = intercalate ";" [ show passed |  (passed, total) <- map count bs ]
