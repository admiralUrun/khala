package dao

import javax.inject.Inject

class Dao @Inject()(dS: DoobieStore)  {
  private val xa = dS.getXa

}
