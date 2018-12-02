package com.github.nnnnusui.servlet.server.enums

enum class Method(val text: String) {
    GET(    "GET")
   ,POST(   "POST")
   ,OPTIONS("OPTIONS")
   ,HEAD(   "HEAD")
   ,PUT(    "PUT")
   ,DELETE( "DELETE")
   ,TRACE(  "TRACE")
   ,CONNECT("CONNECT")
    ;
}
/*
  GET     | URLで指定されたリソース(HTMLファイルや画像など)を取得する
  POST    | 掲示板での投稿など、クライアントからサーバーにある程度のサイズのデータを送信する際に使用する
  OPTIONS | このサーバで使用できるメソッドの一覧を知ることができる
  HEAD    | GETと同じだが、ヘッダのみを返す。必要な情報がヘッダのみで分かる場合、余計な通信を発生させずに済む
  PUT     | URLで指定したサーバ上のファイルを書き換える。POSTでも可能だが、PUTはこれ以上のことをしてはならないと定められている
  DELETE  | URLで指定したサーバ上のファイルを削除する
  TRACE   | サーバは、レスポンスボディにリクエストを詰めてそのまま返信する。テスト用のメソッドである
  CONNECT | 将来の使用に向けて予約されている
*/
