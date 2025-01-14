class FloatingWindowService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var floatingView: View
    private lateinit var selectionView: SelectionView
    
    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        
        // 创建悬浮按钮
        floatingView = LayoutInflater.from(this).inflate(R.layout.floating_button, null)
        
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )
        
        params.gravity = Gravity.END or Gravity.CENTER_VERTICAL
        windowManager.addView(floatingView, params)
        
        // 创建选择区域视图
        selectionView = SelectionView(this)
        
        floatingView.setOnClickListener {
            windowManager.addView(selectionView, params)
        }
    }
    
    override fun onBind(intent: Intent?): IBinder? = null
} 