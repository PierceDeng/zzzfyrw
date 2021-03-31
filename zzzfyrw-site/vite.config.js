import vue from '@vitejs/plugin-vue'
const {resolve} = require('path')

const dev_config = {

  publicDir:'public',
  server: {
    host:'0.0.0.0',
    strictPort:true,
    port:3005,
    cors: true,
    proxy:{},
  },
  resolve: {
    alias: [{ find: '@', replacement: resolve(__dirname, './src') }],
  },
  css:{
    preprocessorOptions:{
      scss: {}
    }
  },
  build:{
    outDir: 'dist',
    assetsDir: './src/assets',
    assetsInlineLimit: 4096,
    sourcemap: true,
    minify:'terser',
    brotliSize: true,
  },
  base:'/',
  logLevel:'info',
  plugins: [
      vue(),
  ]

}


const prd_config = {

  publicDir:'public',
  server: {
    host:'0.0.0.0',
    strictPort:false,
    port:3005,
    cors: true,
    proxy:{


    },
  },
  build:{
    outDir: 'dist',
    assetsDir: './src/assets',
    assetsInlineLimit: 4096,
    sourcemap: true,
    minify:'terser',
    brotliSize: true,
  },
  base:'/',
  logLevel:'info',
  plugins: [vue()]

}




export default ({ command, mode }) => {
  if (command === 'serve' && mode === 'dev') {
    return dev_config
  } else if(mode === 'prd-line'){
    return prd_config
  }
}
