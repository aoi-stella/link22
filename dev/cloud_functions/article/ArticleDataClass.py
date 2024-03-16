class Article:
   """記事クラス
   """
   def __init__(self, title: str, link: str, summary: str):
      """コンストラクタ

      Args:
         title (str): 記事のタイトル
         link (str): 記事のリンク
         summary (str): 記事の概要
      """
      self.title = title
      self.link = link
      self.summary = summary