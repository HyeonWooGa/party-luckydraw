/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  swcMinify: true,
  async rewrites() {
    return [
      {
        source: "/:path*",
        destination:
          "http://ec2-13-125-113-82.ap-northeast-2.compute.amazonaws.com:8000/:path*",
      },
    ];
  },
};

module.exports = nextConfig;
