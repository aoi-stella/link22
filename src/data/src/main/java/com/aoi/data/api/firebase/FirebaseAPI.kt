package com.aoi.data.api.firebase

import com.google.firebase.firestore.FirebaseFirestore

/**
 * FirebaseAPI
 *
 * FirestoreのAPIを提供するクラス
 * 本クラスはシングルトン構成とする。(FirebaseFirestore.getInstance()がstaticメソッドであるため)
 *
 * また、本クラス内のメソッドは全て結果を返すのみとする。
 * 本処理によるログ記録などは上位レイヤーで行うこととする。
 */
object FirebaseAPI {
    //dbのインスタンス
    private lateinit var db: FirebaseFirestore

    /**
     * インスタンスを初期化する
     */
    fun init(){
        db = FirebaseFirestore.getInstance()
    }
    /**
     * ドキュメントを作成する
     *
     * @param collectionName コレクション名
     * @param data 作成するドキュメントのデータ
     * @param callback 作成結果を受け取るコールバック
     */
    fun createDocument(collectionName: String, data: Map<String, Any>, callback: (Result<String>) -> Unit) {
        db.collection(collectionName)
            .add(data)
            //作成に成功した場合
            .addOnSuccessListener { documentReference ->
                //作成したドキュメントIDを返却する
                callback(Result.success(documentReference.id))
            }
            //作成に失敗した場合
            .addOnFailureListener { e ->
                //例外を返却する
                callback(Result.failure(e))
            }
    }

    /**
     * ドキュメントを読み取る
     *
     * @param collectionName コレクション名
     * @param documentId ドキュメントID
     * @param callback 読み取り結果を受け取るコールバック
     */
    fun readDocument(collectionName: String, documentId: String, callback: (Result<Map<String, Any>>) -> Unit) {
        db.collection(collectionName)
            .document(documentId)
            .get()
            //読み取りに成功した場合
            .addOnSuccessListener { document ->
                // 読み取りデータが存在した場合
                if (document.exists()) {
                    //読み取り結果を返却する
                    callback(Result.success(document.data!!))
                }
                // 読み取りデータが存在しなかった場合
                else {
                    //例外を返却する
                    callback(Result.failure(Exception("Document not found")))
                }
            }
            //読み取りに失敗した場合
            .addOnFailureListener { e ->
                //例外を返却する
                callback(Result.failure(e))
            }
    }

    /**
     * コレクション内の全てのドキュメントを読み取る
     *
     * @param collectionName コレクション名
     * @param callback 読み取り結果を受け取るコールバック
     */
    fun readAllDocument(collectionName: String, callback: (Result<List<Map<String, Any>>>) -> Unit) {
        db.collection(collectionName)
            .get()
            //読み取りに成功した場合
            .addOnSuccessListener { documents ->
                //読み取り結果を返却する
                callback(Result.success(documents.map { it.data }))
            }
            //読み取りに失敗した場合
            .addOnFailureListener { e ->
                //例外を返却する
                callback(Result.failure(e))
            }
    }

    /**
     * ドキュメントを更新する
     *
     * @param collectionName コレクション名
     * @param documentId ドキュメントID
     * @param data 更新するデータ
     * @param callback 更新結果を受け取るコールバック
     */
    fun updateDocument(collectionName: String, documentId: String, data: Map<String, Any>, callback: (Result<Unit>) -> Unit) {
        db.collection(collectionName)
            .document(documentId)
            .update(data)
            //更新に成功した場合
            .addOnSuccessListener {
                //更新結果を返却する
                callback(Result.success(Unit))
            }
            //更新に失敗した場合
            .addOnFailureListener { e ->
                //例外を返却する
                callback(Result.failure(e))
            }
    }

    /**
     * ドキュメントを削除する
     *
     * @param collectionName コレクション名
     * @param documentId ドキュメントID
     * @param callback 削除結果を受け取るコールバック
     */
    fun deleteDocument(collectionName: String, documentId: String, callback: (Result<Unit>) -> Unit) {
        db.collection(collectionName)
            .document(documentId)
            .delete()
            //削除に成功した場合
            .addOnSuccessListener {
                //削除結果を返却する
                callback(Result.success(Unit))
            }
            //削除に失敗した場合
            .addOnFailureListener { e ->
                //例外を返却する
                callback(Result.failure(e))
            }
    }
}