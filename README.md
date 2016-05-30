# Common-toolsとは（Overview）
Playアプリケーションからの参照を想定しており、
共通的に利用可能な機能、オブジェクトのチェック、日付操作、フィールド操作、リスト操作、数値操作、文字列操作等、
ネイティブコードで完結する機能を提供します。



## インストール（Install）
利用方法は、build.sbtに下記依存性の定義を記述いただくことで利用可能です。
```
libraryDependencies ++= Seq(
  "yokohama.yellow_man" % "common-tools_2.11" % "1.0.0"
)

resolvers += "Maven Repository on Github" at "http://yellow-man.github.io/common-tools/"
```



## ドキュメント（Document）

* [Common-tools アプリケーション API仕様](http://yellow-man.github.io/common-tools/javadoc/common-tools_2.11/1.0.0/)



## 更新履歴（Version history）

* [1.0.0](https://github.com/yellow-man/common-tools/releases/tag/1.0.0) リリース 2016/05/31



## ライセンス（License）
The MIT License (MIT)

Copyright (c) 2016 yellow-man.yokohama

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

