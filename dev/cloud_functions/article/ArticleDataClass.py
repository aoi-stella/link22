class Article:
   """記事クラス
   """
   def __init__(self, 
                title: str,
                link: str,
                summary: str,
                publishFrom: str,
                publishDate: str,
                thumbnailPath: str,
                publisher: str = "",
                translated_title: str = "",
                translated_summary: str = ""):
      """コンストラクタ

      Args:
         title (str): 記事のタイトル
         link (str): 記事のリンク
         summary (str): 記事の概要
         publishFrom (str): 記事の発行元URL
         publishDate (str): 記事の発行日
         thumbnailPath (str, optional): サムネイルのパス
         publisher (str, optional): 記事の発行元名 / デフォルトは""
         translated_title (str, optional): 翻訳後のタイトル / デフォルトは""
         translated_summary (str, optional): 翻訳後の概要 / デフォルトは""
      """
      self.title = title
      self.link = link
      self.summary = summary
      self.publishFrom = publishFrom
      self.publishDate = publishDate
      self.thumbnailPath = thumbnailPath
      self.publisher = publisher
      self.translated_title = translated_title
      self.translated_summary = translated_summary