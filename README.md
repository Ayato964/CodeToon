# CodeToon
このアプリケーションはJAVA17の導入が必須。


## パッケージ内容

### argument
コードの引数を特定の方に変換するパッケージ。

* IntegerArgument

    文字列を整数型に変換する。

* StringArgument
    
    文字列をString型に変換する。
* BooleanArgument

    文字列を真偽型に変換する
* ObjectArgument

    文字列を特定のオブジェクトに変換する

### main

メインクラスが格納されている。

### map

画面に描画するシーンを格納する。

例えば、Title.javaはタイトル画面を描画する。

カスタムする場合、必ずMapクラスを継承すること

### method

メソッドの中身を管理するパッケージ

例えば、Print.javaは文字列をMessageに出力する機能を持っている。

MyMethodを必ず継承すること。

継承した場合、以下のようなメソッドを継承する。

* newInstance - 自分の新しいインスタンスを生成する。
* set - HashMapに引数が文字列で格納されている。

    そこから取りたいものをStringで取り出すこと。
* action or returnAction
  - actionには戻り値なしの実行結果を出力
  - returnActionには戻り値ありの実行結果を出力
  - 注意：必ずsetで文字列で取り出し、
  
  actionでargumentパッケージをもちい、変換すること

### regi
ファクトリーメソッドを管理するパッケージ

### server
通信関連を管理するパッケージ

### util
変換ツールや便利なクラスを一括管理する。

#### util.animation

Graphicsに文字や画像を挿入する際の補助クラス

createで描画したいGraphicsを渡すこと。


