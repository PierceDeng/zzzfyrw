import vue from '@vitejs/plugin-vue'
// https://vitejs.dev/config/

const dev_config = {

  publicDir:'public',
  server: {
    host:'0.0.0.0',
    strictPort:true,
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
  plugins: [
      vue(),
  ]

}


const prd_config = {

  publicDir:'public',
  server: {
    host:'0.0.0.0',
    strictPort:true,
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
