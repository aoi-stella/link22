class Article:
   """記事クラス
   """
   def __init__(self, 
                title: str,
                link: str,
                summary: str,
                publishFrom: str,
                publishDate: str,
                publisher: str = ""):
      """コンストラクタ

      Args:
         title (str): 記事のタイトル
         link (str): 記事のリンク
         summary (str): 記事の概要
         publishFrom (str): 記事の発行元URL
         publishDate (str): 記事の発行日
         publisher (str, optional): 記事の発行元名 / デフォルトは""
      """
      self.title = title
      self.link = link
      self.summary = summary
      self.publishFrom = publishFrom
      self.publishDate = publishDate
      self.publisher = publisher